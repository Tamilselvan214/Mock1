import axios from 'axios';

// 1. Create an instance with a base URL
const api = axios.create({
  baseURL: 'https://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

// 2. Add the Request Interceptor
api.interceptors.request.use(
  (config) => {
    // Retrieve token from localStorage (or your preferred storage)
    const token = localStorage.getItem('token');

    // If token exists, add it to the Authorization header
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    // Handle request errors here
    return Promise.reject(error);
  }
);

// 3. Optional: Add a Response Interceptor (for global error handling)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    // If the server returns 401 (Unauthorized), you might want to log the user out
    if (error.response && error.response.status === 401) {
      console.error('Token expired or invalid');
      localStorage.removeItem('token');
      // window.location.href = '/login'; // Redirect if needed
    }
    return Promise.reject(error);
  }
);

export default api;