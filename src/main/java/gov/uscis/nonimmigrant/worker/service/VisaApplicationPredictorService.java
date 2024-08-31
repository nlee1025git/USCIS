package gov.uscis.nonimmigrant.worker.service;

import gov.uscis.nonimmigrant.worker.domain.VisaApplicationPredictor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisaApplicationPredictorService {
    private final VisaApplicationPredictor visaApplicationPredictor;

    @Autowired
    public VisaApplicationPredictorService() {
        this.visaApplicationPredictor = new VisaApplicationPredictor(4);
    }

    public double predictApprovalProbability(double[] applicantFeatures) {
        return visaApplicationPredictor.predict(applicantFeatures);
    }

    public double[] trainModel(double[] weights, double label, int iterations, double learningRate) {
        return visaApplicationPredictor.gradientDescent(weights, label, iterations, learningRate);
    }
}
