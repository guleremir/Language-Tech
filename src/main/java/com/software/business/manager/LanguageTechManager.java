package com.software.business.manager;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.software.business.service.LanguageTechService;
import com.software.dto.request.tech.AddLanguageTechRequest;
import com.software.dto.request.tech.DeleteLanguageTechRequest;
import com.software.dto.request.tech.UpdateLanguageTechRequest;
import com.software.dto.response.tech.GetAllLanguageTechResponse;
import com.software.dto.response.tech.GetByIdLanguageTechResponse;
import com.software.entities.LanguageTech;
import com.software.repository.LanguageTechRepository;

@Service
public class LanguageTechManager implements LanguageTechService {

	@Autowired
	private LanguageTechRepository languageTechRepository;

	@Autowired
	@Qualifier("requestMapper")
	private ModelMapper requestMapper;

	@Autowired
	@Qualifier("responseMapper")
	private ModelMapper responseMapper;

	@Override
	public List<GetAllLanguageTechResponse> getAllLanguageTech() {
		return languageTechRepository.findAll().stream().map(
				t -> requestMapper.map(t, GetAllLanguageTechResponse.class))
				.toList();

	}

	@Override
	public GetByIdLanguageTechResponse getByIdLanguageTech(int id) {
		Optional<LanguageTech> languageTech = languageTechRepository
				.findById(id);
		return responseMapper.map(languageTech,
				GetByIdLanguageTechResponse.class);
	}

	@Override
	public void updateLanguageTech(
			UpdateLanguageTechRequest updateLanguageTechRequest) {
		LanguageTech languageTech = requestMapper.map(updateLanguageTechRequest,
				LanguageTech.class);
		languageTechRepository.save(languageTech);

	}

	@Override
	public void addLanguageTech(AddLanguageTechRequest addLanguageTechRequest) {
		LanguageTech languageTech = requestMapper.map(addLanguageTechRequest,
				LanguageTech.class);
		languageTechRepository.save(languageTech);

	}

	@Override
	public void deleteLanguageTech(
			DeleteLanguageTechRequest deleteLanguageTechRequest) {
		LanguageTech languageTech = requestMapper.map(deleteLanguageTechRequest,
				LanguageTech.class);
		languageTechRepository.delete(languageTech);
	}

}
