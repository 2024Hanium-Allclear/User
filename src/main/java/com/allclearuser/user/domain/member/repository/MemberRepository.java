package com.allclearuser.user.domain.member.repository;

import com.allclearuser.user.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Student, Long> {

    Student findByLoginId(String loginId);
}
