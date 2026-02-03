import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";
import "../styles/otp.css";

export default function SignupOtp() {
  const [otp, setOtp] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const verifyOtp = async () => {
    try {
      await api.post("/auth/register/verify-otp", {
        email: localStorage.getItem("signupEmail"),
        otp
      });
      navigate("/login");
    } catch {
      setError("Invalid OTP");
    }
  };

  return (
    <div className="otp-container">
      <h1>Enter OTP</h1>
      {error && <p className="otp-error">{error}</p>}

      <input
        maxLength="6"
        value={otp}
        onChange={(e) => setOtp(e.target.value)}
        required
      />

      <button onClick={verifyOtp}>Verify</button>
    </div>
  );
}
