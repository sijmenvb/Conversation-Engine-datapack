scoreboard players add rec CE_rec 1
scoreboard players set bool CE_suc 0
execute store success score bool CE_suc as @e[tag=CE_npc,distance=..1.5] run function conversation_engine:messages/talk
execute if score bool CE_suc matches 0 unless score rec CE_rec matches 5 if block ~ ~ ~ air positioned ^ ^ ^1 run function conversation_engine:villager_detection/raycast
