# Machine Learning Studies

I compare logistic regression and decision trees for telecom customer churn, and PCA/LDA representations with support vector classification for human activity recognition.

## Technologies

Python, pandas, NumPy, scikit-learn, Matplotlib, Seaborn, Jupyter.

## Files

- `customer-churn.ipynb`
- `human-activity-recognition.ipynb`

## Run

Install `pip install -r requirements.txt`, then launch `jupyter notebook`. The churn notebook downloads the IBM Telco CSV. Extract the UCI HAR dataset into `data/UCI HAR Dataset`, or set `HAR_DATASET_DIR`, before running the activity notebook. Run cells from top to bottom.

## Scope and limitations

HAR input data is not included. Churn missing-value imputation fits the training partition only; evaluation remains exploratory. No deployment service or newly verified accuracy score is claimed.
