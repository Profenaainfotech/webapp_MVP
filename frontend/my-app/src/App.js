import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Signup from "./pages/Signup";
import SignupOtp from "./pages/SignupOtp";
import Login from "./pages/Login";
import LoginOtp from "./pages/LoginOtp";
import Profile from "./pages/Profile";
import Navbar from "./component/Navbar";


function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/signup-otp" element={<SignupOtp />} />
        <Route path="/login" element={<Login />} />
        <Route path="/login-otp" element={<LoginOtp />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
