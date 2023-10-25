import csv
from typing import Dict, List
import numpy as np  # type : ignore
import matplotlib.pyplot as plt  # type : ignore


def read_results(filename: str) -> \
        Dict[str, Dict[int, List[float]]]:
    results: Dict[str, Dict[int, List[float]]] = dict()
    with open(filename, 'r') as f:
        reader = csv.DictReader(f)
        for row in reader:
            algorithm: str = row['Algorithm']
            n: int = int(row['Size'])
            t: float = float(row['Time'])
            if algorithm not in results:
                results[algorithm] = dict()
            if n not in results[algorithm]:
                results[algorithm][n] = list()
            results[algorithm][n].append(t)
        return results


def compute_mean_std(raw: Dict[int, List[float]]) -> \
        np.ndarray:
    result = np.zeros((len(raw), 3))
    for i, n in enumerate(sorted(raw)):
        result[i, 0] = n
        result[i, 1] = np.mean(raw[n])
        result[i, 2] = np.std(raw[n], ddof=1)
    return result


def write_latex_tabular(res: np.ndarray,
                        filename: str):
    with open(filename, 'w') as f:
        f.write(r'\begin{tabular}{rrr}' + '\n')
        f.write(r'$n$ & Average (s) & ' + \
                'Standard deviation (s)')
        f.write(r'\\\hline' + '\n')
        for i in range(res.shape[0]):
            fields = [str(int(res[i, 0])),
                      f'{res[i, 1]:.6f}',
                      f'{res[i, 2]:.6f}']
            f.write(' & '.join(fields) + r'\\' + '\n')
        f.write(r'\end{tabular}' + '\n')


def plot_algorithms(res: Dict[str, np.ndarray],
                    filename: str):
    (fig, ax) = plt.subplots()
    #Add all implemented algorithms here:
    algorithms = ['elementaryMultiplication', 'elementaryMultiplicationTransposed', 'tiledMultiplication']
    for algorithm in algorithms:
        ns = res[algorithm][:, 0]
        means = res[algorithm][:, 1]
        stds = res[algorithm][:, 2]
        ax.errorbar(ns, means, stds, marker='o',
                    capsize=3.0)
    ax.set_xlabel('Size of matrix $n$')
    ax.set_ylabel('Time (s)')
    ax.set_xscale('log')
    ax.set_yscale('log')
    #Add all implemented algorithms here:
    ax.legend(['elementaryMultiplication algorithm',
               'elementaryMultiplicationTransposed algorithm',
               'tiledMultiplication algorithm'])
    fig.savefig(filename)


if __name__ == '__main__':
    # Matrix Multiplication 
    raw_results: Dict[str, Dict[int, List[float]]] = \
        read_results('matrixMultiplicationBenchmark.csv')
    refined_results: Dict[str, np.ndarray] = dict()
    for algorithm in raw_results:
        refined_results[algorithm] = \
            compute_mean_std(raw_results[algorithm])
    
    write_latex_tabular(refined_results['elementaryMultiplication'],
                        'elementaryMultiplication_tabular.tex')
    write_latex_tabular(refined_results['elementaryMultiplicationTransposed'],
                        'elementaryMultiplicationTransposed_tabular.tex')
    write_latex_tabular(refined_results['tiledMultiplication'],
                        'tiledMultiplication_tabular.tex')
    plot_algorithms(refined_results, 'matrix_Multiplication_plot.pdf')
    
    #Transpose matrix
    raw_results: Dict[str, Dict[int, List[float]]] = \
        read_results('TransposeMatrixBenchmark.csv')
    refined_results: Dict[str, np.ndarray] = dict()
    for algorithm in raw_results:
        refined_results[algorithm] = \
            compute_mean_std(raw_results[algorithm])

    write_latex_tabular(refined_results['Transpose'],
                        'matrix_Transpose_tabular.tex')
    
    #Recursive Transpose matrix
    raw_results: Dict[str, Dict[int, List[float]]] = \
        read_results('RecursiveTransposeBenchmark.csv')
    refined_results: Dict[str, np.ndarray] = dict()
    for algorithm in raw_results:
        refined_results[algorithm] = \
            compute_mean_std(raw_results[algorithm])
    write_latex_tabular(refined_results['RecTranspose'],
                        'matrix_RecTranspose_tabular.tex')