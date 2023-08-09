import React, { useState } from 'react';
import axios from 'axios';

function LoginForm() {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const [formErrors, setFormErrors] = useState({
    email: '',
    password: ''
  });

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const validateForm = () => {
    let isValid = true;
    const newFormErrors:  { email: string; password: string;} = {email: "", password: ""};

    // Validate email
    if (!formData.email) {
      isValid = false;
      newFormErrors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      isValid = false;
      newFormErrors.email = 'Invalid email format';
    }

    // Validate password
    if (!formData.password) {
      isValid = false;
      newFormErrors.password = 'Password is required';
    }

    setFormErrors(newFormErrors);
    return isValid;
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (validateForm()) {
      // Perform API call using Axios or Fetch here
      try {
        await axios.post('http://localhost:8080/v1/user/signin', {
          email: formData.email,
          password: formData.password,
        });
        // Handle successful form submission
        alert('Signup successful!');
      } catch (error) {
        // Handle error in form submission
        alert('Login failed. Please try again later.');
      }
    }
  };

  return (
    <div
      style={{
        width: '90%',
        margin: '25px 50px',
        padding: '20px',
        borderRadius: '15px',
        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
        background: 'linear-gradient(to right, #c4d3f6, #f3e7ff)',
      }}
    >
      <form onSubmit={handleSubmit}>
        <h3 className="text-center mb-4">Login</h3>
        <div className="mb-3">
          <input
            type="email"
            name="email"
            className="form-control"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
          />
          {formErrors.email && (
            <div className="text-danger">{formErrors.email}</div>
          )}
        </div>
        <div className="mb-3">
          <input
            type="password"
            name="password"
            className="form-control"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
          />
          {formErrors.password && (
            <div className="text-danger">{formErrors.password}</div>
          )}
        </div>
        <button type="submit" className="btn btn-primary w-100">
          Submit
        </button>
      </form>
    </div>
  );
};

export default LoginForm;
