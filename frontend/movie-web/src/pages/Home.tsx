import SignupForm from '../components/SignupForm';
import LoginForm from '../components/LoginForm';

function HomePage() {
  return (
      <>
      {/* First column */}
      <div className="col-lg-8 text-center shadow-lg p-3 mb-5 bg-body rounded mt-4 pt-5">
        <img src="movie.png" alt="movie_screen"/>
      </div>

      {/* Second column */}
      <div className="col-lg-4">
        <LoginForm />
        <SignupForm />
      </div>
      </>
  );
};

export default HomePage;
