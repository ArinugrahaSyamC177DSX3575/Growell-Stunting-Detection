from flask import Flask, request, jsonify
import numpy as np
from tensorflow import keras
import requests
import tensorflow as tf
from sklearn.preprocessing import StandardScaler

app = Flask(__name__)

model = tf.keras.models.load_model('model.h5')

scaler = StandardScaler()

# API predict data stunting
@app.route('/api/predict', methods=['POST'])
def predict():
    try:
        data = request.json
        
        gender = data['gender']
        age = data['age']
        height = data['height']
        weight = data['weight']

        if not all([gender, age, height, weight]):
            return jsonify(error='Missing required fields'), 400
        
        # Data Preprocessing
        if gender == 'L':
            gender_encoded = np.array([1, 0])
        else:
            gender_encoded = np.array([0, 1])

        age_height_weight = np.array([age, height, weight], dtype=float)

        input_data = np.concatenate([gender_encoded, age_height_weight])

        array_reshaped = input_data.reshape(-1, 1)

        scaled_array = np.copy(input_data)
        scaled_array[2:] = scaler.fit_transform(array_reshaped[2:]).flatten()

        input_data = scaled_array.reshape(1, -1)
        input_data = tf.convert_to_tensor(input_data, dtype=tf.float32)

        # Data Prediction 
        prediction = model.predict(input_data)

        if prediction > 0.5:
            status = 'Tidak Stunting'
        else:
            status = 'Stunting'
        
        response = {
            'status': status
        }

        return jsonify(response)
    
    except Exception:
        return jsonify(error='Failed to save stunting entry'), 500
    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)