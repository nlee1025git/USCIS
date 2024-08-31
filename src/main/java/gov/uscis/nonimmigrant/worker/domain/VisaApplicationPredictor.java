package gov.uscis.nonimmigrant.worker.domain;

public class VisaApplicationPredictor {
    private double[] weights;

    public VisaApplicationPredictor(int numWeights) {
        this.weights = new double[numWeights];
    }

    public double predict(double[] features) {
        double z = 0.0;
        for (int i = 0; i < weights.length; i++) {
            z += weights[i] * features[i];
        }
        return sigmoid(z);
    }

    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public double[] gradientDescent(double[] features, double label, int iterations, double learningRate) {
        for (int iter = 0; iter < iterations; iter++) {
            double prediction = predict(features);
            double[] gradient = new double[weights.length];

            for (int i = 0; i < weights.length; i++) {
                gradient[i] += (prediction - label) * features[i];
            }
            for (int i = 0; i < weights.length; i++) {
                weights[i] -= learningRate * gradient[i];
            }
        }
        return weights;
    }
}
