package br.com.api_diploma.dto;

import br.com.api_diploma.model.UserRole;

public record RegisterDTO(String login, String senha, UserRole role) {}
