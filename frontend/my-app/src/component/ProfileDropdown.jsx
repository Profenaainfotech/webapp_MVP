import { useNavigate } from "react-router-dom";

export default function ProfileDropdown({ letter }) {
  const navigate = useNavigate();

  return (
    <div className="profile-icon">
      {letter}
      <div className="dropdown">
        <p onClick={() => navigate("/profile")}>Profile</p>
        <p onClick={() => {
          localStorage.clear();
          navigate("/");
        }}>Logout</p>
      </div>
    </div>
  );
}
