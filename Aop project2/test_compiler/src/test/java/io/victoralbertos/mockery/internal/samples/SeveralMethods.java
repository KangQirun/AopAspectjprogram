package io.victoralbertos.mockery.internal.samples;

import io.victoralbertos.mockery.api.built_in_interceptor.Bypass;
import io.victoralbertos.mockery.api.built_in_mockery.DTO;
import io.victoralbertos.mockery.api.built_in_mockery.Valid;
import io.victoralbertos.mockery.internal.Mockeries;
import io.victoralbertos.mockery.internal.Model;
import java.util.List;

import static io.victoralbertos.mockery.api.built_in_mockery.Valid.Template.STRING;

@Bypass
public interface SeveralMethods {
  @Valid(STRING)
  List<String> method1(@Valid(STRING) String s1,
      @DTO(Mockeries.MockModel.class) List<Model> models);

  @Valid(STRING)
  List<String> method2(@Valid(STRING) String s1,
      @DTO(Mockeries.MockModel.class) List<Model> models);
}
