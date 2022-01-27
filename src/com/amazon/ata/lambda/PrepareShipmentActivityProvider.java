package com.amazon.ata.lambda;

import com.amazon.ata.activity.PrepareShipmentRequest;
import com.amazon.ata.dependency.DaggerServiceComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class PrepareShipmentActivityProvider implements RequestHandler<PrepareShipmentRequest, String> {

    @Override
    public String handleRequest(PrepareShipmentRequest input, Context context) {
        return DaggerServiceComponent.create().providePrepareShipmentActivity()
                .handleRequest(input, context);
    }
}
