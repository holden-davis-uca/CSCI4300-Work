import sys
import pygame
pygame.init()

width = 1024
height = 768
pygame.display.set_caption("Manager")
managerscreen = pygame.display.set_mode((width, height))
managerscreen.fill((128, 128, 128))



while True:
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
    pygame.display.update()
