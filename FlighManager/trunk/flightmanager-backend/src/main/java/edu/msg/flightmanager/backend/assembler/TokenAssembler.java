package edu.msg.flightmanager.backend.assembler;

import edu.msg.flightmanager.backend.dto.TokenDto;
import edu.msg.flightmanager.backend.model.Token;

/**
 * The class converts from Token model object to dto object, and reverse
 */
public class TokenAssembler {

	public static TokenDto modelToDto(Token token) {
		TokenDto tokenDto = new TokenDto();
		tokenDto.setUuid(token.getUuid());
		tokenDto.setId(token.getId());
		tokenDto.setDeleted(token.isDeleted());
		tokenDto.setValue(token.getValue());
		tokenDto.setCreatedAt(token.getCreatedAt());
		tokenDto.setUser(UserAssembler.modelToDto(token.getUser()));
		tokenDto.setVersion(token.getVersion());
		return tokenDto;
	}

	public static Token dtoToModel(TokenDto tokenDto) {
		Token token = new Token();

		token.setUuid(tokenDto.getUuid());
		token.setId(tokenDto.getId());
		token.setDeleted(tokenDto.isDeleted());
		token.setValue(tokenDto.getValue());
		token.setCreatedAt(tokenDto.getCreatedAt());
		token.setUser(UserAssembler.dtoToModel(tokenDto.getUser()));
		token.setVersion(tokenDto.getVersion());
		return token;
	}

}
