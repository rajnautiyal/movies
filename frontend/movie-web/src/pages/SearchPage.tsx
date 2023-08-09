import SearchForm from '../components/SearchForm';
import MovieList from '../components/movies/MovieList';

function SearchPage() {
    const movies = [
      {
        id: 1,
        name: "Biutiful",
        category: "Actor -- Leading Role",
        additionInfo: "Biutiful {'Uxbal'}",
        won: "NO"
      },
      {
        id: 2,
        name: "True Grit",
        category: "Actor -- Leading Role",
        additionInfo: "True Grit {'Rooster Cogburn'}",
        won: "NO"
      },
      {
        id: 3,
        name: "The Social Network",
        category: "Actor -- Leading Role",
        additionInfo: "The Social Network {'Mark Zuckerberg'}",
        won: "NO"
      }
    ];
      
    return (    
        <div className="container">
            {/* First column */}
            <div className='row'>
            <div className="col-sm-3">
                <SearchForm/>
            </div>
            {/* Second column */}
            <div className="col-sm-9 mt-4 px-1">
                <MovieList movies={movies} />
            </div>
            </div>
        </div>
    );
};

export default SearchPage;
