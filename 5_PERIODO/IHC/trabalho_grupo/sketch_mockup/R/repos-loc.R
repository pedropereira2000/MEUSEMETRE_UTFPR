library(tidyverse)

rl <- read_csv("repos-loc.csv")

rl %>%
  filter(language == 'C#') %>%
  ggplot(aes(y=loc)) +
  scale_y_log10() +
  geom_boxplot()

total <- rl %>%
  filter(language == 'total')

  