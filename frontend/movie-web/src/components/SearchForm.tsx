import axios from "axios";
import { useState } from "react";

const apiUrl = 'http://localhost:8000/search/movies';

interface MovieSearchFilter {
  text: string;
}

function SearchForm() {

  const [filters, setFilters] = useState<MovieSearchFilter>({
    text: ''
  });

  const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = event.target;
    setFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value,
    }));
  };

  const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    axios.post(apiUrl, filters)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };


  return (
      <form onSubmit={handleFormSubmit} style={
        {
            width: '90%',
            margin: '25px 0px',
            padding: '10px',
            borderRadius: '15px',
            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
            background: 'linear-gradient(to right, #c4d3f6, #f3e7ff)',
          }
    }>
        <div className="mb-3">
          <label htmlFor="text">Text</label>
          <input type="text" className="form-control" id="text" name="text" value={filters.text} onChange={handleFilterChange} />
        </div>
        <button type="submit" className="btn btn-primary">Search</button>
      </form>
  );
}

export default SearchForm;