package com.bse.common.customAnnotator.models;

import java.util.List;
import java.util.UUID;

public record OrderLine(UUID id, List<String> states) {

}
