#always run by the player

# reset some scoreboards
scoreboard players set rec CE_rec 0
# initialize the raycast at eye height.
execute at @s positioned ~ ~1.5 ~ run function conversation_engine:villager_detection/raycast