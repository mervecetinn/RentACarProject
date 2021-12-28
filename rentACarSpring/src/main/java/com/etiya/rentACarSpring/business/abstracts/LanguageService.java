package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.LanguageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateLanguageRequest;
import com.etiya.rentACarSpring.business.requests.create.CreateMessageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteLanguageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.entities.Language;

import java.util.List;

public interface LanguageService {
    Result add(CreateLanguageRequest createLanguageRequest);
    Result update(UpdateLanguageRequest updateLanguageRequest);
    Result delete(DeleteLanguageRequest deleteLanguageRequest);
    DataResult<List<LanguageSearchListDto>> getAll();
    Result checkIfLanguageIdExists(int languageId);
}
