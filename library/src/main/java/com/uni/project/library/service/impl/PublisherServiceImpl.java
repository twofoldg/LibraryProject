package com.uni.project.library.service.impl;

import com.uni.project.library.entity.Publisher;
import com.uni.project.library.exception.NotFoundExceptionCustom;
import com.uni.project.library.repository.PublisherRepository;
import com.uni.project.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    @Override
    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new NotFoundExceptionCustom(String.format("Publisher not found with ID %d", id)));
    }

    @Override
    public void createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionCustom(String.format("Publisher not found with ID %d", id)));
        publisherRepository.delete(publisher);
    }
}
