package com.movie.api.domain.mapper;



import com.movie.api.domain.model.User;
import com.movie.api.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public UserDTO mapToDTO(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(source.getEmployee_id());
        userDTO.setUsername(source.getEmail());
        return userDTO;
    }

}
