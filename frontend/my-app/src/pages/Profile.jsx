import "../styles/profile.css";

export default function Profile() {
  const firstLetter = localStorage.getItem("name") || "G";

  return (
    <div className="profile-page">
      {/* LEFT SIDEBAR */}
      <div className="profile-sidebar">
        <div className="profile-avatar">
          {firstLetter}
        </div>

        <h3 className="profile-username">Girith</h3>
        <p className="profile-view-link">View public profile</p>

        <ul className="profile-menu">
          <li className="active">Profile</li>
          <li>Photo</li>
          <li>Account Security</li>
          <li>Subscriptions</li>
          <li>Payment methods</li>
          <li>Privacy</li>
          <li>Notification Preferences</li>
          <li>API clients</li>
          <li>Close account</li>
        </ul>
      </div>

      {/* RIGHT CONTENT */}
      <div className="profile-content">
        <div className="profile-header">
          <h1>Public profile</h1>
          <p>Add information about yourself</p>
        </div>

        {/* BASICS */}
        <section className="profile-section">
          <h3>Basics:</h3>

          <input type="text" value="Girith" />
          <input type="text" placeholder="Last Name" />

          <div className="headline-wrapper">
            <input type="text" placeholder="Headline" />
            <span className="char-count">60</span>
          </div>

          <p className="hint-text">
            Add a professional headline like, "Instructor at Udemy" or "Architect."
          </p>
        </section>

        {/* BIOGRAPHY */}
        <section className="profile-section">
          <h3>Biography</h3>

          <div className="bio-toolbar">
            <strong>B</strong>
            <em>I</em>
          </div>

          <textarea placeholder="Biography"></textarea>

          <p className="hint-text">
            Links and coupon codes are not permitted in this section.
          </p>

          <select>
            <option>English (US)</option>
            <option>English (UK)</option>
            <option>Tamil</option>
          </select>
        </section>

        {/* LINKS */}
        <section className="profile-section">
          <h3>Links:</h3>

          <input placeholder="Website (http(s)://)" />

          <div className="link-input">
            <span>facebook.com/</span>
            <input placeholder="Username" />
          </div>

          <div className="link-input">
            <span>instagram.com/</span>
            <input placeholder="Username" />
          </div>

          <div className="link-input">
            <span>linkedin.com/</span>
            <input placeholder="Public Profile URL" />
          </div>

          <div className="link-input">
            <span>tiktok.com/</span>
            <input placeholder="@Username" />
          </div>

          <div className="link-input">
            <span>x.com/</span>
            <input placeholder="Username" />
          </div>

          <div className="link-input">
            <span>youtube.com/</span>
            <input placeholder="Username" />
          </div>
        </section>

        <button className="profile-save-btn">Save</button>
      </div>
    </div>
  );
}
