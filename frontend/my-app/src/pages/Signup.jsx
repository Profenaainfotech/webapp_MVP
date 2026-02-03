import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";
import "../styles/signup.css";

export default function Signup() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const submit = async () => {
    await api.post("/auth/register/request-otp", {
      name,
      email
    });

    localStorage.setItem("signupEmail", email);
    navigate("/signup-otp");
  };

  return (
    <div className="signup-container">
      <h1>Sign up</h1>

      <input
        placeholder="Full name"
        required
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        placeholder="Email"
        required
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <button onClick={submit}>Continue</button>
    </div>
  );
}
