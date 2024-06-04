import pandas as pd
import matplotlib.pyplot as plt

# mude o nome do arquivo para o que deseja plotar
df = pd.read_csv('plot/mse/mse_values_fold-7.csv', header=None)

df.columns = ['Iteration', 'MSE']

print(df.columns)

if 'Iteration' in df.columns and 'MSE' in df.columns:
    plt.figure(figsize=(10, 6))
    plt.plot(df['Iteration'], df['MSE'], label='MSE')
    plt.xlabel('Iteration')
    plt.ylabel('MSE')
    plt.title('MSE over Iterations')
    plt.legend()
    plt.grid(True)
    plt.show()
else:
    print("As colunas 'Iteration' e 'MSE' não foram encontradas no arquivo CSV.")
