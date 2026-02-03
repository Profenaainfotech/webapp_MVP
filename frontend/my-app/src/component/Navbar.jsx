import { useNavigate } from "react-router-dom";
import "../styles/navbar.css";

export default function Navbar() {
  const navigate = useNavigate();

  const token = localStorage.getItem("token");
  const name = localStorage.getItem("name");

  const logout = () => {
    localStorage.clear();
    navigate("/");
    window.location.reload();
  };

  return (
    <div className="navbar">
      <h2 className="navbar-logo">Profenaa Web-app</h2>

      {!token ? (
        <div className="navbar-actions">
          <button onClick={() => navigate("/login")}>Log in</button>
          <button onClick={() => navigate("/signup")}>Sign up</button>
        </div>
      ) : (
        <div className="navbar-profile">
          <div className="profile-circle">{name}</div>
          <div className="profile-dropdown">
            <p onClick={() => navigate("/profile")}>Profile</p>
            <p onClick={logout}>Logout</p>
          </div>
        </div>
      )}
    </div>
  );
}
