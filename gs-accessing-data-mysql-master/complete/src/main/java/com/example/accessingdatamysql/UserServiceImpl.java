package com.example.accessingdatamysql;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.accessingdatamysql.User;
import com.example.accessingdatamysql.UserRepository;

@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserRepository userRepository;
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	

}
