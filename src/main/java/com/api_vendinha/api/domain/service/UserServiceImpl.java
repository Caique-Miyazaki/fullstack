package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço de usuários.
 * <p>
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     * <p>
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        user.setIs_active(userRequestDto.getIs_active());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());



        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setIs_active(savedUser.getIs_active());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setCpf_cnpj(savedUser.getCpf_cnpj());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto atualizar(UserRequestDto userRequestDto, Long id) {

        // Cria uma nova instância de User.
        User user = userRepository.findById(id).orElseThrow();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setIs_active(userRequestDto.getIs_active());


        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setCpf_cnpj(savedUser.getCpf_cnpj());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIs_active(savedUser.getIs_active());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto buscar(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setCpf_cnpj(user.getCpf_cnpj());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setName(user.getName());
        userResponseDto.setIs_active(user.getIs_active());

       return userResponseDto;
    }

    @Override
    public UserResponseDto atualizarStatus(boolean is_active, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setIs_active(is_active);
        User savedUser = userRepository.save(user);


        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(savedUser.getId());
        userResponseDto.setCpf_cnpj(savedUser.getCpf_cnpj());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setIs_active(savedUser.getIs_active());
        return userResponseDto;
    }


    @Override
    public List<User> listar() {


        return  userRepository.findAll();
    }
}
