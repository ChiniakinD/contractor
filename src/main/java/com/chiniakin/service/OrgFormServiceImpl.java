package com.chiniakin.service;

import com.chiniakin.repository.OrgFormRepository;
import lombok.RequiredArgsConstructor;
import com.chiniakin.entity.OrgForm;
import com.chiniakin.model.OrgFormModel;
import com.chiniakin.service.interfaces.OrgFormService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с формами организаии.
 *
 * @author ChiniakinD.
 */
@Service
@RequiredArgsConstructor
public class OrgFormServiceImpl implements OrgFormService {

    private final OrgFormRepository orgFormRepository;
    private final ModelMapper mapper;

    @Autowired
    @Qualifier("mergeMapper")
    private final ModelMapper mergeMapper;

    @Override
    public List<OrgFormModel> getAllOrgForms() {
        return orgFormRepository.findAll()
                .stream()
                .map(orgForm -> mapper.map(orgForm, OrgFormModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrgFormModel> getActiveOrgForms() {
        return orgFormRepository.findAll()
                .stream()
                .filter(OrgForm::isActive)
                .map(orgForm -> mapper.map(orgForm, OrgFormModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrgFormModel getOrgFormById(Long id) {
        return mapper.map(orgFormRepository.findByIdOrThrow(id), OrgFormModel.class);
    }

    @Override
    public void updateOrgForm(Long id, OrgFormModel orgFormModel) {
        OrgForm orgForm = orgFormRepository.findById(id)
                .orElseGet(() -> {
                    OrgForm newOrgForm = new OrgForm();
                    newOrgForm.setId(id);
                    return newOrgForm;
                });
        mergeMapper.map(orgFormModel, orgForm);
        orgFormRepository.save(orgForm);
    }

    @Override
    public void deleteOrgFormById(Long id) {
        OrgForm orgForm = orgFormRepository.findByIdOrThrow(id);
        orgForm.setActive(false);
        orgFormRepository.save(orgForm);
    }

}
