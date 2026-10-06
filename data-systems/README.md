# Data Systems Experiments

I explore file-format tradeoffs, Parquet partitioning, replicated storage planning, and a simulated HDFS block-loss model.

## Technologies

Python, pandas, NumPy, PyArrow, Matplotlib, Jupyter.

## Files

- `hdfs-failure-simulation.ipynb`
- `partitioning.ipynb`
- `storage-formats.ipynb`
- `storage-planning.ipynb`

## Run

Install `pip install -r requirements.txt`, then `jupyter notebook`. Run notebooks in a dedicated scratch directory. Storage-format and partitioning exercises generate large local files; use a smaller `n_rows` or `n_sales` for quick checks.

## Scope and limitations

The HDFS exercise is a local simulation, not a running Hadoop cluster. Partitioning compares disk reads with in-memory filtering, so the timing is not a controlled speedup benchmark. Storage prices are hard-coded exercise inputs, not current cloud pricing. `partitioning.ipynb` replaces its named generated folders on rerun.

## Tableau workbook

`video-game-sales.twb` explores video-game sales through publisher and genre views. It references an external `vgsales clean.csv` source and a Hyper extract; neither is supplied. Open the workbook in a compatible Tableau version and reconnect the data before use. The workbook has not been rendered in this environment.
