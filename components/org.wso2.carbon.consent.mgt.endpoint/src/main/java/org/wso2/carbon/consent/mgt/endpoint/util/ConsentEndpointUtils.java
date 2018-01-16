/*
 *
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.consent.mgt.endpoint.util;

import org.wso2.carbon.consent.mgt.core.ConsentManager;
import org.wso2.carbon.consent.mgt.core.constant.ConsentConstants;
import org.wso2.carbon.consent.mgt.core.model.Purpose;
import org.wso2.carbon.consent.mgt.endpoint.dto.ErrorDTO;
import org.wso2.carbon.consent.mgt.endpoint.dto.PurposeListResponseDTO;
import org.wso2.carbon.consent.mgt.endpoint.dto.PurposeRequestDTO;
import org.wso2.carbon.consent.mgt.endpoint.exception.BadRequestException;
import org.wso2.carbon.consent.mgt.endpoint.exception.InternalServerErrorException;
import org.wso2.carbon.context.PrivilegedCarbonContext;

/**
 * This class is used to define the utilities require for Consent Management Web App.
 */
public class ConsentEndpointUtils {

    public static ConsentManager getConsentManager() {
        return (ConsentManager) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ConsentManager.class, null);
    }

    /**
     * This method is used to create an InternalServerErrorException with the known errorCode.
     * @param code Error Code.
     * @return a new InternalServerErrorException with default details.
     */
    public static InternalServerErrorException buildInternalServerErrorException(String code) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(code);
        errorDTO.setMessage(ConsentConstants.STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT);
        errorDTO.setDescription(ConsentConstants.STATUS_INTERNAL_SERVER_ERROR_DESCRIPTION_DEFAULT);
        return new InternalServerErrorException(errorDTO);
    }

    /**
     * This method is used to create a BadRequestException with the known errorCode and message.
     * @param description Error Message Desription.
     * @param code Error Code.
     * @return BadRequestException with the given errorCode and description.
     */
    public static BadRequestException buildBadRequestException(String description, String code) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(code);
        errorDTO.setMessage(ConsentConstants.STATUS_BAD_REQUEST_MESSAGE_DEFAULT);
        errorDTO.setDescription(description);
        return new BadRequestException(errorDTO);
    }

    public static Purpose getPurposeRequest(PurposeRequestDTO purposeRequestDTO) {
        return new Purpose(purposeRequestDTO.getPurpose(), purposeRequestDTO.getDescription());
    }

    public static PurposeListResponseDTO getPurposeListResponse(Purpose purposeResponse) {
        PurposeListResponseDTO purposeListResponseDTO = new PurposeListResponseDTO();
        purposeListResponseDTO.setPurposeId(String.valueOf(purposeResponse.getId()));
        purposeListResponseDTO.setPurpose(purposeResponse.getName());
        purposeListResponseDTO.setDiscripiton(purposeResponse.getDescription());
        return purposeListResponseDTO;
    }
}
