# Pattern Recognition Studies

I compare linear SVM and XGBoost models for binary wine quality and explore distributions and statistical tests in the Palmer Penguins dataset.

## Technologies

Python, scikit-learn, XGBoost, pandas, NumPy, SciPy, Matplotlib, Seaborn.

## Files

- `penguin-analysis.ipynb`
- `wine-quality.ipynb`

## Run

Install `pip install -r requirements.txt`, then `jupyter notebook`. Place the UCI wine-quality red and white CSVs in `data/` with their standard filenames. The penguin notebook uses `palmerpenguins`.

## Scope and limitations

Wine data is not included. A large-C linear SVM is an approximation to a hard-margin configuration, not proof of separability. The evaluation uses a single holdout, not cross-validation. Notebook outputs are cleared for reproducibility.
