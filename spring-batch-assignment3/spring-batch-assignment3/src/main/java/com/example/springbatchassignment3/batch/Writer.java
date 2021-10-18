//package com.example.springbatchassignment3.batch;
//
//import java.util.List;
//
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.springbatchassignment3.model.Student;
//import com.example.springbatchassignment3.repository.StudentRepository;
//
//@Component
//public class Writer implements ItemWriter<Student>{
//	
//	@Autowired
//	private StudentRepository studentRepository;
//
//	@Override
//	public void write(List<? extends Student> items) throws Exception {
//		// TODO Auto-generated method stub
//		studentRepository.saveAll(items);
//	}
//
//}
