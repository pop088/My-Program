import unittest
import compute_highest_affinity

class TestStringMethods(unittest.TestCase):

  def test1(self):
    site_list = ["a.com", "b.com", "a.com", "b.com", "a.com", "c.com"]
    user_list = ["andy", "andy", "bob", "bob", "charlie", "charlie"]
    time_list = [1238972321, 1238972456, 1238972618, 1238972899, 1248472489, 1258861829] 
    self.assertEqual(
      compute_highest_affinity.highest_affinity(site_list, user_list, time_list),("a.com", "b.com"))


if __name__ == '__main__':
    unittest.main()