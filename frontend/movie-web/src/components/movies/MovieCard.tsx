import React from 'react';
import { Link } from 'react-router-dom';

interface Movie {
  id: string,
  name: string;
  category: string;
  additionInfo: string;
  won: string;
}

interface Props {
  movie: Movie;
}

const MovieCard: React.FC<Props> = ({ movie }) => {
  return (
    <Link to={`/movies/${movie.id}`} style={{ textDecoration: 'none' }}>
    <div className={`card bg-secondary`}>
      <div className="card-body">
        
          <h5 className="card-title">{movie.name}</h5>
          <p className="card-text">{movie.additionInfo}</p>
          <p className="card-text">Award Won? <b>{movie.won}</b></p>
        
      </div>
    </div>
    </Link>
  );
};

export default MovieCard;
