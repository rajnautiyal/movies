import React from 'react';
import MovieCard from './MovieCard';

interface Movie {
  title: string;
  description: string;
  color: string;
}

interface Props {
  movies: Movie[];
}

const MovieList: React.FC<Props> = ({ movies }) => {
  return (
    <div className="row">
      {movies.map((movie, index) => (
        <div key={index} className="col-md-6 col-lg-4 mb-4">
          <MovieCard movie={movie} />
        </div>
      ))}
    </div>
  );
};

export default MovieList;
