import Footer from '../components/Footer';
import { Outlet } from 'react-router';
import MainNavigation from '../components/MainNavigation';

function MainLayout(){
  return (
    <>
        <MainNavigation/>
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

export default MainLayout;
