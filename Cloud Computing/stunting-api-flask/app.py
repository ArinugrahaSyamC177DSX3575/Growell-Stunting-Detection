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
    data = request.json
    
    name = data['name']
    gender = data['gender']
    age = data['age']
    height = data['height']
    weight = data['weight']

    if not all([name, gender, age, height, weight]):
        return jsonify(error='Missing required fields'), 400
    
    gender_encoded = np.where(gender == 'L', [0.0, 1.0], [1.0, 0.0])

    age_height_weight = np.array([age, height, weight], dtype=float)

    scaled_age_height_weight = scaler.transform(age_height_weight.reshape(-1, 1)).flatten()

    input_data = np.concatenate([gender_encoded, scaled_age_height_weight])

    input_data = input_data.reshape(1, -1)

    input_data = tf.convert_to_tensor(input_data, dtype=tf.float32)

    prediction = model.predict(input_data)

    if prediction > 0.5:
        status = 'tidak stunting'
    else:
        status = 'stunting'
    
    url = 'https://growell-express-api-fkegjceqka-et.a.run.app/api/statusstunting'
    payload = {
        'name': name,
        'gender': gender,
        'age': age,
        'height': height,
        'weight': weight,
        'status': status
    }
    response = requests.post(url, json=payload)
    
    if response.status_code == 201:
        # Jika permintaan berhasil, mengembalikan respons dari API Express sebagai respons dari API Flask
        return jsonify(response.json())
    else:
        # Jika permintaan gagal, mengembalikan pesan error
        return jsonify(error='Failed to save stunting entry'), 500
    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
