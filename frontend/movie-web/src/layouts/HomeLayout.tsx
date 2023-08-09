import HomeNavigation from '../components/HomeNavigation';
import Footer from '../components/Footer';
import { Outlet } from 'react-router';

function Home(){
  return (
    <>
        <HomeNavigation/>
        <div></div>
        <div className="container mt-5">
            <br/>
            <div className="row">
                <Outlet/>
            </div>
        </div>
        <Footer/>
    </>
  );
};

export default Home;
