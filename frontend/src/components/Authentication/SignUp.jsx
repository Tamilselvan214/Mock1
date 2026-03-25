import {
  Button,
  MenuItem,
  Select,
  TextField,
  Alert,
  FormControl,
  InputLabel,
} from "@mui/material";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../api/api";

function SignUp(props) {
  const { authSwitch, setAuthSwitch } = props;
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    role: "user",
  });

  const [error, setError] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSignUp = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await api.post("/register", formData);
      const { token } = response.data;

      if (token) {
        localStorage.setItem("token", token);
        navigate("/home");
      }
    } catch (err) {
      setError(
        err.response?.data?.message || "Registration failed. Try again.",
      );
    }
  };

  return (
    <div className="grid gap-4 p-6 bg-white rounded-lg shadow-lg w-96">
      <h1 className="text-3xl font-bold text-center pb-4">Sign Up</h1>

      {error && <Alert severity="error">{error}</Alert>}

      <TextField
        label="Username"
        name="username"
        variant="outlined"
        value={formData.username}
        onChange={handleChange}
      />
      <TextField
        label="Email"
        name="email"
        type="email"
        variant="outlined"
        value={formData.email}
        onChange={handleChange}
      />
      <TextField
        label="Password"
        name="password"
        type="password"
        variant="outlined"
        value={formData.password}
        onChange={handleChange}
      />

      <FormControl fullWidth>
        <InputLabel>Role</InputLabel>
        <Select
          label="Role"
          name="role"
          value={formData.role}
          onChange={handleChange}
        >
          <MenuItem value="user">Employee</MenuItem>
          <MenuItem value="admin">Manager</MenuItem>
        </Select>
      </FormControl>

      <Button
        variant="contained"
        color="primary"
        fullWidth
        onClick={handleSignUp}
      >
        Sign Up
      </Button>

      <p
        className="text-blue-500 font-medium text-center underline cursor-pointer"
        onClick={() => {
          setAuthSwitch(!authSwitch);
        }}
      >
        Already Have a Account? sign in
      </p>
    </div>
  );
}

export default SignUp;
