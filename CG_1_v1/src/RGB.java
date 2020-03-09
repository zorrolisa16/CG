import java.awt.Color;

/**
 * @author Polina Astashko
 */
public class RGB {

    private javax.swing.JSlider mRGBSliderB;
    private javax.swing.JSlider mRGBSliderG;
    private javax.swing.JSlider mRGBSliderR;
    private javax.swing.JTextField mRGBTextFieldB;
    private javax.swing.JTextField mRGBTextFieldG;
    private javax.swing.JTextField mRGBTextFieldR;

    float r = 0;
    float g = 0;
    float b = 0;

    public float getR() {
        return r;
    }

    public void setR(float r) {
        if (r < 0) {
            this.r = 0;
        } else if (r > 1) {
            this.r = 1;
        } else {
            this.r = r;
        }
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        if (g < 0) {
            this.g = 0;
        } else if (g > 1) {
            this.g = 1;
        } else {
            this.g = g;
        }
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        if (b < 0) {
            this.b = 0;
        } else if (b > 1) {
            this.b = 1;
        } else {
            this.b = b;
        }
    }

    public Color getColor() {
        return new Color(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255));
    }

    public RGB(javax.swing.JSlider mRGBSliderB, javax.swing.JSlider mRGBSliderG, javax.swing.JSlider mRGBSliderR,
            javax.swing.JTextField mRGBTextFieldB, javax.swing.JTextField mRGBTextFieldG,
            javax.swing.JTextField mRGBTextFieldR) {
        this.mRGBSliderR = mRGBSliderR;
        this.mRGBSliderG = mRGBSliderG;
        this.mRGBSliderB = mRGBSliderB;
        this.mRGBTextFieldR = mRGBTextFieldR;
        this.mRGBTextFieldG = mRGBTextFieldG;
        this.mRGBTextFieldB = mRGBTextFieldB;
    }

    public void textChanged() {
        try {
            double bufR = Double.parseDouble(mRGBTextFieldR.getText());
            double bufB = Double.parseDouble(mRGBTextFieldB.getText());
            double bufG = Double.parseDouble(mRGBTextFieldG.getText());
            if (bufG >= 0 && bufG <= 255 && bufB >= 0 && bufB <= 255 && bufR >= 0 && bufR <= 255) {
                r = (float) bufR / 255;
                g = (float) bufG / 255;
                b = (float) bufB / 255;
            }
        } catch (Exception e) {
            System.out.println("incorrect value");
        }
        mRGBSliderR.setValue(Math.round(r * mRGBSliderR.getMaximum()));
        mRGBSliderG.setValue(Math.round(g * mRGBSliderG.getMaximum()));
        mRGBSliderB.setValue(Math.round(b * mRGBSliderB.getMaximum()));
    }

    public void sliderChanged() {
        r = (float) mRGBSliderR.getValue() / mRGBSliderR.getMaximum();
        g = (float) mRGBSliderG.getValue() / mRGBSliderG.getMaximum();
        b = (float) mRGBSliderB.getValue() / mRGBSliderB.getMaximum();
        mRGBTextFieldR.setText(String.valueOf(Math.round(r * 255)));
        mRGBTextFieldG.setText(String.valueOf(Math.round(g * 255)));
        mRGBTextFieldB.setText(String.valueOf(Math.round(b * 255)));
    }

    public void updateAll() {
        mRGBTextFieldR.setText(String.valueOf(Math.round(r * 255)));
        mRGBSliderR.setValue(Math.round(r * mRGBSliderR.getMaximum()));
        mRGBTextFieldB.setText(String.valueOf(Math.round(b * 255)));
        mRGBSliderB.setValue(Math.round(b * mRGBSliderG.getMaximum()));
        mRGBTextFieldG.setText(String.valueOf(Math.round(g * 255)));
        mRGBSliderG.setValue(Math.round(g * mRGBSliderB.getMaximum()));
    }
}
