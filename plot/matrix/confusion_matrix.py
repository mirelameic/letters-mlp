import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

confusion_matrix = pd.read_csv("plot/matrix/confusion_matrix_fold-4.csv", header=None)

labels = [chr(i) for i in range(65, 91)]  # A-Z

plt.figure(figsize=(10, 7))
sns.heatmap(confusion_matrix, annot=True, fmt="d", cmap="Blues", xticklabels=labels, yticklabels=labels)
plt.xlabel('Predicted Label')
plt.ylabel('True Label')
plt.title('Confusion Matrix')
plt.show()