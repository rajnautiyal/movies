import { Link } from 'react-router-dom';

function MainNavigation(){
  return (
    <>
      {/* Fixed top navigation bar */}
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div className="container">
          <Link className="navbar-brand" to="/">
            <img src='/logo-m.png' alt='Logo'></img> <span><strong>Movie</strong></span>
          </Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ml-auto">
            <li className="nav-item">
                <Link className="nav-link" to="/">Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/search">Movie Search</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/contact-us">Contact</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
};

export default MainNavigation;
