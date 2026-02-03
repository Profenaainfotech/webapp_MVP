import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";
import "../styles/login.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const submit = async () => {
    setError("");

    try {
      await api.post("/auth/login/request-otp", {
        email: email,
        name: "LOGIN"   // required to pass backend validation
      });

      // ✅ Only runs if user is registered
      localStorage.setItem("loginEmail", email);
      navigate("/login-otp");

    } catch (err) {
      // ✅ Show popup error for unregistered email
      if (err.response && err.response.data) {
        setError("This email is not registered. Please sign up first.");
      } else {
        setError("Something went wrong. Please try again.");
      }
    }
  };

  return (
    <div className="login-container">
      <h1>Log in</h1>

      {/* 🔴 ERROR POPUP */}
      {error && (
        <div className="login-error-popup">
          {error}
        </div>
      )}

      <input
        type="email"
        placeholder="Email"
        required
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <button onClick={submit}>Continue</button>
    </div>
  );
}
