from flask import Flask, request, jsonify
import numpy as np
from tensorflow import keras
import requests
import tensorflow as tf
from sklearn.preprocessing import StandardScaler

app = Flask(__name__)

model = tf.keras.models.load_model('model.h5')

scaler = StandardScaler()

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
        
        age_height_weight = np.array([age, height, weight], dtype=float)
        scaler.fit(age_height_weight.reshape(-1, 1))
        
        # Data Preprocessing
        gender_encoded = np.where(gender == 'L', [0.0, 1.0], [1.0, 0.0])
        
        scaled_age_height_weight = scaler.transform(age_height_weight.reshape(-1, 1)).flatten()

        input_data = np.concatenate([gender_encoded, scaled_age_height_weight])
        input_data = input_data.reshape(1, -1)
        input_data = tf.convert_to_tensor(input_data, dtype=tf.float32)

        # Data Prediction 
        prediction = model.predict(input_data)

        if prediction > 0.5:
            status = 'tidak stunting'
        else:
            status = 'stunting'
        
        response = {
            'status': status
        }

        return jsonify(response)
    
    except Exception:
        return jsonify(error='Failed to save stunting entry'), 500
    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
