from flask import Flask, request, jsonify
import requests
import numpy as np
from tensorflow import keras
import requests


app = Flask(__name__)

# Load model.h5
model = keras.models.load_model('model.h5')

# Endpoint untuk menerima permintaan dari aplikasi mobile
@app.route('/api/predict', methods=['POST'])
def predict():
    data = request.json
    
    # Mengambil data dari permintaan
    name = data['name']
    gender = data['gender']
    age = data['age']
    height = data['height']
    weight = data['weight']

    if not all([name, gender, age, height, weight]):
        return jsonify(error='Missing required fields'), 400
    
    # Melakukan prediksi menggunakan model.h5
    input_data = np.array([[gender, age, height, weight]])
    prediction = model.predict(input_data)
    
    if prediction > 0.5:
        status = 'tidak stunting'
    else:
        status = 'stunting'
    
    # Melakukan permintaan POST ke API Express untuk menyimpan data ke database
    url = 'URL_API_EXPRESS_CLOUD_RUN/api/newstuntingentry'
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