import pandas as pd
import matplotlib.pyplot as plt

# Carregar dados do CSV
df = pd.read_csv('plot/mse_values.csv')

# Verificar nomes das colunas
print(df.columns)

# Certificar-se de que os nomes das colunas estão corretos
if 'Iteration' in df.columns and 'MSE' in df.columns:
    # Plotar os dados
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
