import React from 'react';
import {RouterProvider, createBrowserRouter} from 'react-router-dom';

import MainLayout from './layouts/MainLayout';
import HomeLayout from './layouts/HomeLayout';
import HomePage from './pages/Home';
import ContactUsPage from './pages/ContactUs';
import ErrorPage from './pages/ErrorPage';
import SearchPage from './pages/SearchPage';

import './assets/index.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.js';
import MovieDetail from './components/movies/MovieDetail';

const router = createBrowserRouter([
  {
    path: '/search',
    element: <MainLayout/>,
    errorElement: <ErrorPage/>,
    children: [
      { path: '/search', element: <SearchPage/> }
    ]
  },
  {
    path: '/movies',
    element: <MainLayout/>,
    errorElement: <ErrorPage/>,
    children: [
      { path: '/movies/:movieId', element: <MovieDetail/> }
    ]
  },
  {
    path: '/',
    element: <HomeLayout/>,
    errorElement: <ErrorPage/>,
    children: [
      { path: '/', element: <HomePage/> },
      { path: '/search', element: <SearchPage/> },
      { path: '/contact-us', element: <ContactUsPage/> },
      { path: '/movies/:movieId', element: <MovieDetail/> },
    ]
  }
])

const App: React.FC = () => {
  return <RouterProvider router={router}/>
};

export default App;
