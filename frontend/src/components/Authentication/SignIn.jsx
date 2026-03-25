import { Button, TextField, Alert } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom"; 
import api from "../../api/api";

function SignIn(props) {
  const { authSwitch, setAuthSwitch } = props;
  const navigate = useNavigate();

  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });

  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError(""); 

    try {
      const response = await api.post("/login", loginData);

      const { token } = response.data;

      if (token) {
        localStorage.setItem("token", token);

        navigate("/home"); 
      }
    } catch (err) {
      setError(err.response?.data?.message || "Invalid email or password");
    }
  };

  return (
    <div className="grid gap-4 p-6 bg-white rounded-lg shadow-lg w-96">
      <h1 className="text-3xl font-bold text-center pb-4">Sign In</h1>

      {error && <Alert severity="error">{error}</Alert>}

      <TextField
        label="Email"
        type="email"
        required
        variant="outlined"
        value={loginData.email}
        onChange={(e) => {
          setLoginData({ ...loginData, email: e.target.value });
        }}
      />
      <TextField
        label="Password"
        variant="outlined"
        type="password"
        required
        value={loginData.password}
        onChange={(e) => {
          setLoginData({ ...loginData, password: e.target.value });
        }}
      />
      
      <Button
        variant="contained"
        color="primary"
        type="submit"
        fullWidth
        onClick={handleLogin} // Call the login function
      >
        Sign In
      </Button>

      <p
        className="text-blue-500 font-medium text-center underline cursor-pointer"
        onClick={() => {
          setAuthSwitch(!authSwitch);
        }}
      >
        Don't have a Account? Sign up
      </p>
    </div>
  );
}

export default SignIn;