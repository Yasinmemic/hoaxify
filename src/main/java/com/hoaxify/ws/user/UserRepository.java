package com.hoaxify.ws.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created By Yasin Memic on Mar, 2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Page<User> findByUsernameNot(String username, Pageable page);

    @Transactional
    void deleteByUsername(String username);
}
