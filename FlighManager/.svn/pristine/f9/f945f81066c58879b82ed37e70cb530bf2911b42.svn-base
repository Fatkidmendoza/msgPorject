package edu.msg.flightmanager.backend.assembler;

import edu.msg.flightmanager.backend.dto.PlaneDto;
import edu.msg.flightmanager.backend.model.Company;
import edu.msg.flightmanager.backend.model.Plane;
import edu.msg.flightmanager.backend.model.PlaneMake;
import edu.msg.flightmanager.backend.model.PlaneType;

/**
 * The class converts from plane model to dto object, and reverse
 */

public class PlaneAssembler {
	public static PlaneDto modelToDto(Plane plane) {
		PlaneDto planeDto = new PlaneDto();
		planeDto.setUuid(plane.getUuid());
		planeDto.setId(plane.getId());
		planeDto.setCompanyName(plane.getCompany().getName());
		planeDto.setNumberOfPlaces(plane.getNumberOfPlaces());
		planeDto.setType(plane.getType().name());
		planeDto.setDeleted(plane.isDeleted());
		planeDto.setCode(plane.getCode());
		planeDto.setMake(plane.getMake().name());
		planeDto.setVersion(plane.getVersion());
		return planeDto;
	}

	public static Plane dtoToModel(PlaneDto planeDto) {
		Plane plane = new Plane();
		plane.setUuid(planeDto.getUuid());
		plane.setId(planeDto.getId());
		Company company = new Company();
		company.setName(planeDto.getCompanyName());
		plane.setCompany(company);
		plane.setNumberOfPlaces(planeDto.getNumberOfPlaces());
		plane.setType(PlaneType.valueOf(planeDto.getType()));
		plane.setDeleted(planeDto.isDeleted());
		plane.setCode(planeDto.getCode());
		plane.setMake(PlaneMake.valueOf(planeDto.getMake()));
		plane.setVersion(planeDto.getVersion());
		return plane;
	}
}
