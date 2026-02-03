import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";
import "../styles/otp.css";

export default function LoginOtp() {
  const [otp, setOtp] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const verifyOtp = async () => {
    try {
      const res = await api.post("/auth/login/verify-otp", {
        email: localStorage.getItem("loginEmail"),
        otp
      });

      localStorage.setItem("token", res.data.token);
      localStorage.setItem(
        "name",
        localStorage.getItem("loginEmail")[0].toUpperCase()
      );

      navigate("/");
      window.location.reload();
    } catch {
      setError("Invalid OTP");
    }
  };

  return (
    <div className="otp-container">
      <h1>Check your inbox</h1>
      {error && <p className="otp-error">{error}</p>}

      <input
        maxLength="6"
        value={otp}
        onChange={(e) => setOtp(e.target.value)}
        required
      />

      <button onClick={verifyOtp}>Log in</button>
    </div>
  );
}
